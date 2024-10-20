import type { Metadata } from "next";
import { Poppins } from "next/font/google";
import "./globals.css";
import { cn } from "@/lib/utils";
import { AuthContextProvider } from "@/context/auth-context";

const poppins = Poppins({ weight: '300', subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Se Organiza: Seu gerenciador financeiro.",
  description: "Gerenciador Financeiro.",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <AuthContextProvider>
      <html lang="pt-BR">
        <body className={cn(poppins.className, "bg-gray-200")}>{children}</body>
      </html>
    </AuthContextProvider>
  );
}
