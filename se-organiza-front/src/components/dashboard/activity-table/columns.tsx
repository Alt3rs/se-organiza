"use client"

import { Button } from "@/components/ui/button"
import { ColumnDef } from "@tanstack/react-table"

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
      return <Button variant="destructive">Remover</Button>
    }
  }
]
