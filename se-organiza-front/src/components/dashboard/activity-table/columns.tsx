"use client"

import { Button } from "@/components/ui/button"
import { ColumnDef } from "@tanstack/react-table"
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"
import { DeleteIcon, EllipsisIcon, PencilLineIcon } from "lucide-react"

// This type is used to define the shape of our data.
// You can use a Zod schema here if you want.
export type Activity = {
  id: string
  date: Date,
  description: string,
  value: number,
  typeTransaction: "expense" | "revenue", 
  type: "bill" | "study"  | "entertainment" | "food" | "others" | ""
}

export const columns: ColumnDef<Activity>[] = [
  {
    accessorKey: "date",
    header: "Data",
    cell: ({ row }) => {
      const aDate = row.getValue("date") as Date;
      const formatedDate = aDate.getDate() + "/" + (aDate.getMonth() + 1) + "/" + aDate.getFullYear();
      return <p>{formatedDate}</p>
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

      const type = row.getValue("typeTransaction");

      const formatedValue = aValue.toLocaleString("pt-BR",
        { minimumFractionDigits: 2, maximumFractionDigits: 2 });

      const valueClass = (type === "revenue") ? "text-emerald-500" : "text-red-500";

      return <p className={valueClass}>R$ {formatedValue}</p>
    }
  },
  {
    accessorKey: "typeTransaction",
    header: "Tipo da Transação",
    cell: ({ row }) => {
      const type = row.getValue("typeTransaction") as string;

      const valueClass = (type === "revenue") ? "text-emerald-500" : "text-red-500";

      return <p className={valueClass}>{type}</p>
    }
  },
  {
    accessorKey: "type",
    header: "Tipo de gasto",
  },
  {
    accessorKey: "actions",
    header: "Ações",
    cell:({ row }) =>{
      return <div>
        <DropdownMenu>
          <DropdownMenuTrigger>
              <EllipsisIcon />
          </DropdownMenuTrigger>
          <DropdownMenuContent>
            <DropdownMenuLabel>Ações</DropdownMenuLabel>
            <DropdownMenuSeparator />
            <DropdownMenuItem className="text-red-600 gap-2"><DeleteIcon/>Deletar</DropdownMenuItem>
            <DropdownMenuItem className="gap-2"><PencilLineIcon/>Editar</DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      </div>
    }
  }
]
