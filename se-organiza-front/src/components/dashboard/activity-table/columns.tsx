"use client"

import { useState } from "react";
import { Button } from "@/components/ui/button";
import { ColumnDef } from "@tanstack/react-table";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { DeleteIcon, EllipsisIcon, PencilLineIcon } from "lucide-react";
import { Input } from "@/components/ui/input";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";

export type Activity = {
  id: string;
  date: Date;
  description: string;
  value: number;
  type: "expense" | "revenue";
  
};

const SelectEditForm = () => {
  return (
    <div className="flex space-x-4">
      <Select>
        <SelectTrigger className="flex-1 min-w-[200px] w-full">
          <SelectValue placeholder="Selecione o fluxo de caixa" />
        </SelectTrigger>
        <SelectContent>
          <SelectItem value="0">Entrada</SelectItem>
          <SelectItem value="1">Saída</SelectItem>
        </SelectContent>
      </Select>
    </div>
  );
};

const EditModal = ({ isOpen, onClose, activity }: { isOpen: boolean; onClose: () => void; activity: Activity }) => {
  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 overflow-auto">
      <div className="bg-zinc-100 p-6 rounded-xl shadow-lg w-full max-w-3xl">
        <h2 className="uppercase text-zinc-600 font-bold text-xl mb-4">Editar Atividade</h2>
        <form className="flex flex-wrap gap-4">
          <Input 
            type="date" 
            className="flex-1 min-w-[150px]" 
            defaultValue={activity.date.toISOString().split('T')[0]} 
          />
          <Input 
            type="text" 
            placeholder="Insira a descrição da atividade..."
            defaultValue={activity.description}
            className="flex-1 min-w-[200px]"
          />
          <Input 
            type="number" 
            placeholder="Digite o valor"
            defaultValue={activity.value}
            className="flex-1 min-w-[150px]"
          />
          <SelectEditForm />
          <div className="w-full flex justify-center gap-4 mt-4">
            <Button type="submit">Salvar</Button>
            <Button type="button" onClick={onClose}>Cancelar</Button>
          </div>
        </form>
      </div>
    </div>
  );
};

export const columns: ColumnDef<Activity>[] = [
  {
    accessorKey: "date",
    header: "Data",
    cell: ({ row }) => {
      const aDate = row.getValue("date") as Date;
      const formatedDate = aDate.getDate() + "/" + (aDate.getMonth() + 1) + "/" + aDate.getFullYear();
      return <p>{formatedDate}</p>;
    }
  },
  {
    accessorKey: "description",
    header: "Descrição",
  },
  {
    accessorKey: "value",
    header: "Valor",
    cell: ({ row }) => {
      const aValue = row.getValue("value") as number;
      const type = row.getValue("type");
      const formatedValue = aValue.toLocaleString("pt-BR", { minimumFractionDigits: 2, maximumFractionDigits: 2 });
      const valueClass = type === "revenue" ? "text-emerald-500" : "text-red-500";
      return <p className={valueClass}>R$ {formatedValue}</p>;
    }
  },
  {
    accessorKey: "type",
    header: "Tipo",
    cell: ({ row }) => {
      const type = row.getValue("type") as string;
      const valueClass = (type === "revenue") ? "text-emerald-500" : "text-red-500";
      return <p className={valueClass}>{type}</p>;
    }
  },
  {
    accessorKey: "actions",
    header: "Ações",
    cell: ({ row }) => {
      const [isModalOpen, setModalOpen] = useState(false);
      const activity = row.original as Activity;

      return (
        <div>
          <DropdownMenu>
            <DropdownMenuTrigger>
              <EllipsisIcon />
            </DropdownMenuTrigger>
            <DropdownMenuContent>
              <DropdownMenuLabel>Ações</DropdownMenuLabel>
              <DropdownMenuSeparator />
              <DropdownMenuItem
                className="gap-2"
                onClick={() => setModalOpen(true)}
              >
                <PencilLineIcon /> Editar
              </DropdownMenuItem>
              <DropdownMenuItem className="text-red-600 gap-2">
                <DeleteIcon /> Deletar
              </DropdownMenuItem>
            </DropdownMenuContent>
          </DropdownMenu>

          <EditModal
            isOpen={isModalOpen}
            onClose={() => setModalOpen(false)}
            activity={activity}
          />
        </div>
      );
    }
  }
];
