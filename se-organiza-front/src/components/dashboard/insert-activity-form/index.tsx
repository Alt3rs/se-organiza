import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";

export function InsertActivityForm(){
    return(
        <div className="flex space-x-2 p-6">
            <Input type="date" className="max-w-[10rem]" />
            <Input type="text" placeholder="Insira a descrição da atividade..."/>
            <Input type="number" className="w-max" placeholder="Digite o valor"/>
            <Select >
        <SelectTrigger className="w-80">
          <SelectValue placeholder="Selecione o fluxo de caixa" />
        </SelectTrigger>
        <SelectContent>
          <SelectItem value="0">Entrada</SelectItem>
          <SelectItem value="1">Saída</SelectItem>
        </SelectContent>
      </Select>
            <div className="flex items-center">
            <Button>Incluir</Button>
            </div>
        </div>
    );
}