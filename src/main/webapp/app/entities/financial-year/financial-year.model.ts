export interface IFinancialYear {
  id: number;
  year?: number | null;
}

export type NewFinancialYear = Omit<IFinancialYear, 'id'> & { id: null };
