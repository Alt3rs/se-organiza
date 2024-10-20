"use client"

import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { PasswordInput } from '@/components/ui/password-input';
import { CircleDollarSign, MailIcon } from 'lucide-react';
import Link from "next/link"
import { z } from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from '@hookform/resolvers/zod';
import { Form, FormControl, FormField, FormItem, FormMessage } from '@/components/ui/form';
import { frontendApi } from '@/lib/api';
import { LoginResponseType } from '@/app/api/auth/login/route';
import { useContext, useState } from 'react';
import { CustomAlert, CustomAlertType } from '@/components/general/custom-alert';
import { AxiosError } from 'axios';
import { AuthContext } from '@/context/auth-context';
import { useRouter } from 'next/navigation';

const loginFormSchema = z.object({
    email: z.string().email({ message: "Email inválido" }),
    password: z.string().min(1, { message: "Senha inválida" })
});

type LoginFormType = z.infer<typeof loginFormSchema>;

export default function LoginForm() {

    const [message, setMessage] = useState(<></>);

    const authContext = useContext(AuthContext);

    const router = useRouter();

    const loginForm = useForm<LoginFormType>({
        resolver: zodResolver(loginFormSchema),
        defaultValues: {
            email: "",
            password: ""
        }
    });

    async function handleLoginSubmit({ email, password }: LoginFormType) {
        const data = JSON.stringify({
            email,
            password
        });

        try {
            const result = await frontendApi.post("/auth/login", data);

            const { token, error } = result.data as LoginResponseType;

            if (token) {
                authContext.signIn(token);
                router.push("/dashboard");
            }
            else {
                const message = <CustomAlert
                    type={CustomAlertType.ERROR}
                    title="Erro ao logar-se!"
                    message={error || "Erro desconhecido"}
                />;

                setMessage(message);
            }

        } catch (e) {
            const axiosError = e as AxiosError;

            const message = <CustomAlert
                type={CustomAlertType.ERROR}
                title="Erro ao logar-se!"
                message={axiosError.message} />

            setMessage(message);

        }

    }

    return (
        <>
            <div className="flex pb-60 items-center h-screen">

                <div className="container space-y-4 p-8 max-w-md rounded-xl bg-gray-50 shadow-xl">
                    <span className="flex items-center gap-2">
                        <CircleDollarSign className=" text-orange-400" size={48} />
                        <h1 className=" uppercase text-zinc-600 font-bold text-2xl">Se Organiza</h1>
                    </span>

                    <Form {...loginForm}>
                        <form onSubmit={loginForm.handleSubmit(handleLoginSubmit)} className="space-y-4">
                            {message}
                            <FormField
                                control={loginForm.control}
                                name="email"
                                render={({ field }) => {
                                    return (
                                        <FormItem>
                                            <FormControl>
                                                <Input type="text" placeholder="Digite seu email..." suffix={<MailIcon />} {...field} />
                                            </FormControl>
                                            <FormMessage />
                                        </FormItem>
                                    )
                                }}
                            />

                            <FormField
                                control={loginForm.control}
                                name="password"
                                render={({ field }) => {
                                    return (<FormItem>
                                        <FormControl>
                                            <PasswordInput placeholder="Digite sua senha..." {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>)
                                }}
                            />


                            <div className="flex flex-col items-end justify-end">
                                <Button variant="link" asChild>
                                    <Link href="/register">Inscreva-se</Link>
                                </Button>
                            </div>
                            <div className="flex flex-col items-center justify-center">
                                <Button type="submit">Entrar</Button>
                            </div>
                        </form>
                    </Form>

                </div>
            </div>
        </>
    )
}