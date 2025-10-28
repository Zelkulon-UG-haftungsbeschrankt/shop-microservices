// app/api/proxy/[service]/[[...path]]/route.ts
import { NextResponse } from "next/server";

const SERVICES: Record<string, string> = {
    product: process.env.NEXT_PUBLIC_PRODUCT_URL ?? "http://localhost:8081",
    cart:    process.env.NEXT_PUBLIC_CART_URL    ?? "http://localhost:8082",
    order:   process.env.NEXT_PUBLIC_ORDER_URL   ?? "http://localhost:8083",
    payment: process.env.NEXT_PUBLIC_PAYMENT_URL ?? "http://localhost:8084",
};

function parseProxySegments(req: Request) {
    const u = new URL(req.url);
    // Erwartet Pfad wie: /api/proxy/<service>/<...optional path>
    const ix = u.pathname.indexOf("/api/proxy/");
    if (ix === -1) return { service: undefined as string | undefined, tail: [] as string[] };

    const rest = u.pathname.slice(ix + "/api/proxy/".length);
    const parts = rest.split("/").filter(Boolean);
    const service = parts.shift();
    return { service, tail: parts };
}

function buildTargetUrl(base: string, tail: string[], req: Request) {
    const target = new URL(base.replace(/\/+$/, ""));
    const joined = tail.join("/");
    target.pathname = [target.pathname.replace(/\/+$/, ""), joined].filter(Boolean).join("/");
    // Query durchreichen
    const incoming = new URL(req.url);
    incoming.searchParams.forEach((v, k) => target.searchParams.append(k, v));
    return target.toString();
}

async function proxy(req: Request) {
    const { service, tail } = parseProxySegments(req);
    if (!service) return NextResponse.json({ error: "Missing service segment" }, { status: 400 });

    const base = SERVICES[service];
    if (!base) return NextResponse.json({ error: `Unsupported service: ${service}` }, { status: 400 });

    const url = buildTargetUrl(base, tail, req);
    const method = req.method as "GET" | "POST" | "PUT" | "PATCH" | "DELETE" | "HEAD";

    const init: RequestInit = {
        method,
        headers:
            method === "GET" || method === "HEAD"
                ? undefined
                : { "content-type": req.headers.get("content-type") ?? "application/json" },
        body: method === "GET" || method === "HEAD" ? undefined : await req.text(),
    };

    try {
        const res = await fetch(url, init);
        const body = await res.arrayBuffer();
        const out = new NextResponse(body, { status: res.status, headers: res.headers });
        out.headers.set("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate");
        return out;
    } catch (e: any) {
        return NextResponse.json({ error: "Upstream fetch failed", message: String(e), url }, { status: 502 });
    }
}

export const GET = (req: Request) => proxy(req);
export const POST = (req: Request) => proxy(req);
export const PUT = (req: Request) => proxy(req);
export const PATCH = (req: Request) => proxy(req);
export const DELETE = (req: Request) => proxy(req);

// Optional: verhindert Static Optimization
export const dynamic = "force-dynamic";
