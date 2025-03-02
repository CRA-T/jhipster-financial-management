import dayjs from 'dayjs/esm';

import { IPurchaseOrder, NewPurchaseOrder } from './purchase-order.model';

export const sampleWithRequiredData: IPurchaseOrder = {
  id: 2720,
  nameOfTheMinistry: 'de peur de',
  orderNumber: 'concurrence',
  orderDate: dayjs('2025-03-02T08:56'),
};

export const sampleWithPartialData: IPurchaseOrder = {
  id: 17117,
  nameOfTheMinistry: 'au dépens de fidèle',
  orderNumber: 'membre de l’équipe',
  orderDate: dayjs('2025-03-02T02:40'),
  taxRate: 6365.69,
  totalTaxAmount: 28108.83,
  prepaidTaxAmount: 22835.01,
  authExpenditureNumber: 'enlever comme lorsque',
  availableBalance: 29326.2,
};

export const sampleWithFullData: IPurchaseOrder = {
  id: 24613,
  nameOfTheMinistry: 'avare',
  orderNumber: 'que loin',
  orderDate: dayjs('2025-03-01T23:28'),
  totalAmountWithoutTax: 21464.73,
  taxRate: 29227.16,
  totalTaxAmount: 13317.57,
  prepaidTaxAmount: 16284.23,
  totalAmountWithTax: 369.95,
  authExpenditureNumber: 'toc drelin',
  allocatedCredits: 6928.33,
  committedExpenditures: 10353.48,
  availableBalance: 2076.36,
};

export const sampleWithNewData: NewPurchaseOrder = {
  nameOfTheMinistry: 'diablement fourbe placide',
  orderNumber: 'repousser',
  orderDate: dayjs('2025-03-02T09:55'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
