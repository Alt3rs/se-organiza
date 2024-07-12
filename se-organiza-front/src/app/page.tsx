import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { PasswordInput } from '@/components/ui/password-input';
import { CircleDollarSign, MailIcon } from 'lucide-react'; 
import Link from "next/link"

export default function Home() {
  return (
    <main>
      <div className="flex pb-60 items-center h-screen">

        <div className="container space-y-4 p-8 max-w-md rounded-xl bg-gray-50 shadow-xl">
          <span className="flex items-center gap-2">
            <CircleDollarSign className=" text-orange-400" size={48} />
            <h1 className=" uppercase text-zinc-600 font-bold text-2xl">Se Organiza</h1>
          </span>
          <Input type="email" placeholder="Digite seu email..." suffix={<MailIcon/>}/>
          <PasswordInput placeholder="Digite sua senha..."/>
          <div className="flex flex-col items-end justify-end">
            <Button variant="link" asChild>
              <Link href="/signup">Inscreva-se</Link>
            </Button>
          </div>
          <div className="flex flex-col items-center justify-center">
            <Button className="">Entrar</Button>
          </div>
          
        </div>
      </div>
    </main>
  );
}
