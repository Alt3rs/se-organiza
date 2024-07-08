import { CircleDollarSign } from "lucide-react";

export default function DashboardLayout({ children } : { children : React.ReactNode}){
    return(
        <>
            <div className="container flex h-20 rounded-xl items-center m-auto gap-4 shadow-md bg-zinc-800">
                <CircleDollarSign  className="text-orange-500" size={48} />  
                <h1 className=" uppercase text-zinc-100 font-bold text-3xl">Se Organiza</h1>
            </div>
            <div className="container items-center gap-4 w-md m-auto mt-2 rounded-xl shadow-md bg-slate-50">
                {children}
            </div>
        </>
    );
}