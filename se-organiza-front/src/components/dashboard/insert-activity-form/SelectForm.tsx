"use client";
import { useState } from 'react';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';

const SelectForm = () => {
  const [showSecondSelect, setShowSecondSelect] = useState(false);

  const handleFirstSelectChange = (value: string) => {
    setShowSecondSelect(value === '1'); // Show the second select only if the value is '1' (Saída)
  };

  return (
    <div className="flex space-x-2 p-6">
      <Select onValueChange={handleFirstSelectChange}>
        <SelectTrigger className="w-80">
          <SelectValue placeholder="Selecione o fluxo de caixa" />
        </SelectTrigger>
        <SelectContent>
          <SelectItem value="0">Entrada</SelectItem>
          <SelectItem value="1">Saída</SelectItem>
        </SelectContent>
      </Select>

      {showSecondSelect && (
        <Select>
          <SelectTrigger className="w-80">
            <SelectValue placeholder="Selecione o tipo" />
          </SelectTrigger>
          <SelectContent>
            <SelectItem value="contas">Contas</SelectItem>
            <SelectItem value="educacao">Educação</SelectItem>
            <SelectItem value="entretenimento">Entretenimento</SelectItem>
            <SelectItem value="alimentacao">Alimentação</SelectItem>
            <SelectItem value="outros">Outros...</SelectItem>
            <SelectItem value="...">...</SelectItem>
          </SelectContent>
        </Select>
      )}
    </div>
  );
};

export default SelectForm;
