// app/page.tsx
'use client';

import { useEffect, useMemo, useState } from 'react';

type HttpMethod = 'GET' | 'POST' | 'PUT' | 'DELETE';
type ServiceKey = 'product' | 'cart' | 'order' | 'payment';

const SERVICE_BASE: Record<ServiceKey, string> = {
    product: process.env.NEXT_PUBLIC_PRODUCT_URL ?? 'http://localhost:8081',
    cart:    process.env.NEXT_PUBLIC_CART_URL    ?? 'http://localhost:8082',
    order:   process.env.NEXT_PUBLIC_ORDER_URL   ?? 'http://localhost:8083',
    payment: process.env.NEXT_PUBLIC_PAYMENT_URL ?? 'http://localhost:8084',
};

const TABS: Array<{ key: ServiceKey; label: string }> = [
    { key: 'product', label: 'Products' },
    { key: 'cart',    label: 'Cart' },
    { key: 'order',   label: 'Order' },
    { key: 'payment', label: 'Payment' },
];

function cn(...c: (string | false | null | undefined)[]) {
    return c.filter(Boolean).join(' ');
}

function PrettyJson({ data }: { data: unknown }) {
    const str = useMemo(() => {
        try {
            if (data instanceof ArrayBuffer) {
                return '[binary]';
            }
            return JSON.stringify(data, null, 2);
        } catch {
            return String(data);
        }
    }, [data]);
    return (
        <pre className="overflow-auto text-sm leading-relaxed p-4 rounded-xl bg-zinc-950 text-zinc-100 ring-1 ring-zinc-800 max-h-[50vh]">
      {str}
    </pre>
    );
}

type LastRun = {
    url: string;
    method: HttpMethod;
    status: number | null;
    ms: number | null;
    at: string;
};

export default function Page() {
    const [activeTab, setActiveTab] = useState<ServiceKey>('product');

    // Shared request state
    const [method, setMethod] = useState<HttpMethod>('GET');
    const [path, setPath] = useState<string>('products'); // relative zu /api/proxy/<service>/
    const [query, setQuery] = useState<string>(''); // z.B. username=harun
    const [body, setBody] = useState<string>('{}');

    const [loading, setLoading] = useState(false);
    const [resp, setResp] = useState<any>(null);
    const [status, setStatus] = useState<number | null>(null);
    const [timeMs, setTimeMs] = useState<number | null>(null);
    const [lastRuns, setLastRuns] = useState<LastRun[]>([]);

    // Templates je Tab
    useEffect(() => {
        if (activeTab === 'product') {
            setMethod('GET');
            setPath('products');
            setQuery('');
            setBody('{}');
        } else if (activeTab === 'cart') {
            setMethod('POST');
            setPath('cart');
            setQuery('username=johndoe');
            setBody(JSON.stringify({
                productId: 'd08d53aa-30a1-4d60-b93c-8f2e1b6ec9e2',
                productName: 'Premium Fotodruck A4',
                productPrice: 4.99,
                imageLink: 'https://cdn.zelkulon.com/images/products/fotodruck-a4.jpg'
            }, null, 2));
        } else if (activeTab === 'order') {
            setMethod('POST');
            setPath('v1/order');
            setQuery('username=harun');
            setBody(JSON.stringify({
                orderContact: {
                    gender: 'Herr',
                    firstName: 'Harun',
                    lastName: 'Dastekin',
                    street: 'Musterstraße',
                    houseNumber: '42A',
                    postalCode: '12345',
                    city: 'Berlin',
                    email: 'harun@example.com'
                },
                items: [
                    { productId: 'c4a5d8b7-2d4e-4e7b-a8e7-34a9b2c6fabc', productName: 'Visitenkarten Premium 100 Stück', price: 19.99 },
                    { productId: '5b1fcd21-9f62-4c8a-8a6c-11c51c3a95a9', productName: 'Flyer A5 Hochglanz 250g', price: 29.99 }
                ],
                orderRegistry: { username: 'harun', totalAmount: 49.98 },
                totalAmount: 49.98
            }, null, 2));
        } else if (activeTab === 'payment') {
            setMethod('POST');
            setPath('payment');
            setQuery('');
            setBody(JSON.stringify({
                orderId: '7c2b1e40-5d9e-4b9a-b3cd-9d8e1b34c1a1',
                username: 'harun',
                amount: 49.99,
                status: 'PENDING',
                method: 'VORKASSE'
            }, null, 2));
        }
        setResp(null);
        setStatus(null);
        setTimeMs(null);
    }, [activeTab]);

    const serviceUrl = useMemo(() => SERVICE_BASE[activeTab], [activeTab]);
    const proxyUrl = useMemo(() => {
        const url = ['/api/proxy', activeTab, path].filter(Boolean).join('/');
        return query.trim() ? `${url}?${query.trim()}` : url;
    }, [activeTab, path, query]);

    async function run() {
        setLoading(true);
        setResp(null);
        setStatus(null);
        setTimeMs(null);
        const started = performance.now();

        const isBodyless = method === 'GET' || method === 'DELETE';
        let payload: string | undefined = undefined;

        if (!isBodyless) {
            // JSON validieren
            try {
                payload = body?.trim() ? JSON.stringify(JSON.parse(body)) : '{}';
            } catch (e: any) {
                setLoading(false);
                setResp({ error: 'Invalid JSON body', message: String(e) });
                setStatus(0);
                setTimeMs(0);
                return;
            }
        }

        try {
            const res = await fetch(proxyUrl, {
                method,
                headers: isBodyless ? undefined : { 'content-type': 'application/json' },
                body: isBodyless ? undefined : payload,
            });
            const dt = performance.now() - started;
            setStatus(res.status);
            setTimeMs(Math.round(dt));

            const ct = res.headers.get('content-type') || '';
            const data = ct.includes('application/json') ? await res.json() : await res.text();
            setResp(data);

            setLastRuns((prev) => [
                { url: proxyUrl, method, status: res.status, ms: Math.round(dt), at: new Date().toISOString() },
                ...prev.slice(0, 9),
            ]);
        } catch (e: any) {
            const dt = performance.now() - started;
            setStatus(0);
            setTimeMs(Math.round(dt));
            setResp({ error: 'Request failed', message: String(e) });
        } finally {
            setLoading(false);
        }
    }

    function copyCurl() {
        const absolute = typeof window !== 'undefined' ? new URL(proxyUrl, window.location.origin).toString() : proxyUrl;
        let cmd = `curl -X ${method} "${absolute}"`;
        if (!(method === 'GET' || method === 'DELETE')) {
            cmd += ` \\\n  -H "content-type: application/json" \\\n  -d '${body?.trim() || '{}'}'`;
        }
        navigator.clipboard.writeText(cmd);
    }

    return (
        <main className="mx-auto max-w-7xl px-4 py-8">
            {/* Header */}
            <header className="mb-6">
                <h1 className="text-2xl font-semibold tracking-tight">Shop Test Console</h1>
                <p className="text-sm text-zinc-500 mt-1">
                    Proxy über <code className="px-1 py-0.5 rounded bg-zinc-100 text-zinc-800">/api/proxy/&lt;service&gt;/…</code> – Ziele aus ENV.
                </p>
            </header>

            {/* Service Tabs */}
            <nav className="mb-6 flex gap-2 flex-wrap">
                {TABS.map(t => (
                    <button
                        key={t.key}
                        className={cn(
                            'rounded-2xl px-4 py-2 text-sm font-medium ring-1 transition',
                            activeTab === t.key
                                ? 'bg-zinc-900 text-white ring-zinc-900'
                                : 'bg-white text-zinc-700 ring-zinc-200 hover:bg-zinc-100'
                        )}
                        onClick={() => setActiveTab(t.key)}
                    >
                        {t.label}
                    </button>
                ))}
            </nav>

            {/* Environment banner */}
            <section className="mb-6 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-3">
                {Object.entries(SERVICE_BASE).map(([k, v]) => (
                    <div key={k} className="rounded-xl border border-zinc-200 p-3 bg-white">
                        <div className="text-xs uppercase text-zinc-500 mb-1">{k}</div>
                        <div className="text-sm break-all">{v}</div>
                    </div>
                ))}
            </section>

            {/* Request Builder Card */}
            <section className="rounded-2xl border border-zinc-200 bg-white shadow-sm">
                <div className="p-4 border-b border-zinc-200 flex flex-col md:flex-row gap-3 md:items-center">
                    <div className="flex items-center gap-2">
                        <select
                            value={method}
                            onChange={(e) => setMethod(e.target.value as HttpMethod)}
                            className="h-10 rounded-xl border border-zinc-300 bg-white px-3 text-sm"
                        >
                            <option>GET</option>
                            <option>POST</option>
                            <option>PUT</option>
                            <option>DELETE</option>
                        </select>

                        <div className="text-sm text-zinc-600 hidden md:block">
                            /api/proxy/<span className="font-mono">{activeTab}</span>/
                        </div>

                        <input
                            value={path}
                            onChange={(e) => setPath(e.target.value)}
                            placeholder="path (z. B. products | cart | v1/order | paymentStatus/<id>)"
                            className="h-10 flex-1 rounded-xl border border-zinc-300 bg-white px-3 text-sm w-[320px]"
                        />
                    </div>

                    <div className="flex-1" />

                    <div className="flex items-center gap-2">
                        <input
                            value={query}
                            onChange={(e) => setQuery(e.target.value)}
                            placeholder="query (z. B. username=harun)"
                            className="h-10 rounded-xl border border-zinc-300 bg-white px-3 text-sm w-[280px]"
                        />
                        <button
                            onClick={copyCurl}
                            className="h-10 rounded-xl border border-zinc-300 bg-white px-3 text-sm hover:bg-zinc-50"
                            title="Copy curl"
                        >
                            Copy curl
                        </button>
                        <button
                            onClick={run}
                            disabled={loading}
                            className={cn(
                                'h-10 rounded-xl px-4 text-sm font-medium',
                                loading ? 'bg-zinc-300 text-zinc-600' : 'bg-zinc-900 text-white hover:bg-black'
                            )}
                        >
                            {loading ? 'Sending…' : 'Send'}
                        </button>
                    </div>
                </div>

                {/* Body (for POST/PUT) */}
                {(method === 'POST' || method === 'PUT') && (
                    <div className="p-4 border-b border-zinc-200">
                        <label className="block text-sm font-medium mb-2 text-zinc-700">Request Body (JSON)</label>
                        <textarea
                            value={body}
                            onChange={(e) => setBody(e.target.value)}
                            spellCheck={false}
                            className="w-full min-h-[160px] rounded-xl border border-zinc-300 bg-zinc-50 p-3 font-mono text-sm"
                        />
                    </div>
                )}

                {/* URL Preview */}
                <div className="px-4 py-3 bg-zinc-50 border-b border-zinc-200 text-xs text-zinc-700">
                    <div className="flex flex-col sm:flex-row sm:items-center gap-2">
                        <div className="font-mono break-all">
                            {proxyUrl}
                        </div>
                        <span className="hidden sm:inline-block mx-2 text-zinc-400">→</span>
                        <div className="text-zinc-600">
                            Proxied an <span className="font-mono">{serviceUrl}</span>
                        </div>
                    </div>
                </div>

                {/* Response */}
                <div className="p-4 grid grid-cols-1 lg:grid-cols-3 gap-4">
                    <div className="lg:col-span-2">
                        <div className="text-sm font-medium mb-2 text-zinc-700">Response</div>
                        {resp === null ? (
                            <div className="rounded-xl border border-dashed border-zinc-300 p-6 text-sm text-zinc-500">
                                Noch keine Antwort. Sende eine Anfrage.
                            </div>
                        ) : (
                            <PrettyJson data={resp} />
                        )}
                    </div>

                    <div>
                        <div className="text-sm font-medium mb-2 text-zinc-700">Meta</div>
                        <div className="rounded-xl border border-zinc-200">
                            <div className="grid grid-cols-2 text-sm">
                                <div className="px-3 py-2 text-zinc-500">Status</div>
                                <div className="px-3 py-2">{status ?? '-'}</div>
                                <div className="px-3 py-2 text-zinc-500 border-t border-zinc-200">Dauer</div>
                                <div className="px-3 py-2 border-t border-zinc-200">{timeMs !== null ? `${timeMs} ms` : '-'}</div>
                                <div className="px-3 py-2 text-zinc-500 border-t border-zinc-200">Methode</div>
                                <div className="px-3 py-2 border-t border-zinc-200">{method}</div>
                            </div>
                        </div>

                        <div className="mt-4">
                            <div className="text-sm font-medium mb-2 text-zinc-700">Letzte Requests</div>
                            <div className="rounded-xl border border-zinc-200 divide-y">
                                {lastRuns.length === 0 && (
                                    <div className="px-3 py-3 text-sm text-zinc-500">Leer</div>
                                )}
                                {lastRuns.map((r, i) => (
                                    <div key={i} className="px-3 py-2 text-sm">
                                        <div className="flex items-center gap-2">
                                            <span className="px-2 py-0.5 rounded bg-zinc-900 text-white text-[11px]">{r.method}</span>
                                            <span className="text-zinc-800 break-all">{r.url}</span>
                                        </div>
                                        <div className="text-xs text-zinc-500 mt-1">
                                            {r.status} · {r.ms} ms · {new Date(r.at).toLocaleTimeString()}
                                        </div>
                                    </div>
                                ))}
                            </div>
                        </div>

                    </div>
                </div>
            </section>

            {/* Quick Actions je Tab */}
            <section className="mt-6 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-3">
                {/* Products */}
                <QuickAction
                    label="GET /products"
                    active={activeTab === 'product'}
                    onClick={() => {
                        setActiveTab('product');
                        setMethod('GET'); setPath('products'); setQuery(''); setBody('{}'); setResp(null);
                    }}
                />
                {/* Cart */}
                <QuickAction
                    label="POST /cart?username=johndoe"
                    active={activeTab === 'cart'}
                    onClick={() => {
                        setActiveTab('cart');
                        setMethod('POST'); setPath('cart'); setQuery('username=johndoe');
                    }}
                />
                {/* Order */}
                <QuickAction
                    label="POST /v1/order?username=harun"
                    active={activeTab === 'order'}
                    onClick={() => {
                        setActiveTab('order');
                        setMethod('POST'); setPath('v1/order'); setQuery('username=harun');
                    }}
                />
                {/* Payment */}
                <QuickAction
                    label="POST /payment"
                    active={activeTab === 'payment'}
                    onClick={() => {
                        setActiveTab('payment');
                        setMethod('POST'); setPath('payment'); setQuery('');
                    }}
                />
            </section>

            <footer className="mt-10 text-xs text-zinc-500">
                Tip: Variablen in <code>.env</code> anpassen (NEXT_PUBLIC_* URLs). Proxy-Route bleibt gleich.
            </footer>
        </main>
    );
}

function QuickAction({ label, onClick, active }: { label: string; onClick: () => void; active: boolean }) {
    return (
        <button
            onClick={onClick}
            className={cn(
                'w-full text-left rounded-xl border p-3 transition',
                active
                    ? 'border-zinc-900 bg-zinc-900 text-white'
                    : 'border-zinc-200 bg-white hover:bg-zinc-50'
            )}
        >
            <div className="text-[13px]">{label}</div>
            <div className={cn('text-xs mt-1', active ? 'text-zinc-200' : 'text-zinc-500')}>Preset laden</div>
        </button>
    );
}
