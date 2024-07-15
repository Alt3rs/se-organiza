import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import SelectForm from "./SelectForm";

export function InsertActivityForm(){
    return(
        <div className="flex space-x-2 p-6">
            <Input type="date" className="max-w-[10rem]" />
            <Input type="text" placeholder="Insira a descrição da atividade..."/>
            <Input type="number" className="w-max" placeholder="Digite o valor"/>
            <SelectForm/>
            <div className="flex items-center">
            <Button>Incluir</Button>
            </div>
        </div>
    );
}