import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";

export function InsertActivityForm(){
    return(
        <div className="flex space-x-2 p-6">
            <Input type="date" className="max-w-[10rem]" />
            <Input type="text" placeholder="Insira a descrição da atividade..."/>
            <Input type="number" className="w-max" placeholder="Digite o valor"/>
            <Select>
                <SelectTrigger className="w-80">
                    <SelectValue placeholder="Selecione o fluxo de caixa" />
                </SelectTrigger>
                <SelectContent>
                    <SelectItem value="0">Entrada</SelectItem>
                    <SelectItem value="1">Saída</SelectItem>
                </SelectContent>
            </Select>
            <Select>
                <SelectTrigger className="w-80">
                    <SelectValue placeholder="Selecione o tipo" />
                </SelectTrigger>
                <SelectContent>
                    <SelectItem value="0">Contas</SelectItem>
                    <SelectItem value="1">Educação</SelectItem>
                    <SelectItem value="2">Entretenimento</SelectItem>
                    <SelectItem value="3">Alimentação</SelectItem>
                    <SelectItem value="4">Outros...</SelectItem>
                    <SelectItem value="5">...</SelectItem>
                </SelectContent>
            </Select>
            <Button>Incluir</Button>
        </div>
    );
}