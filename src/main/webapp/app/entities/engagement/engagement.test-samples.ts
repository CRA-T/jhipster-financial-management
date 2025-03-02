import dayjs from 'dayjs/esm';

import { IEngagement, NewEngagement } from './engagement.model';

export const sampleWithRequiredData: IEngagement = {
  id: 10403,
  engagementNumber: 'à peu près tellement rapide',
  engagementDate: dayjs('2025-03-02T11:37'),
};

export const sampleWithPartialData: IEngagement = {
  id: 31196,
  engagementNumber: 'simplifier sur même si',
  engagementDate: dayjs('2025-03-02T09:23'),
  objectOfExpense: "à l'égard de afin que",
  notifiedCredits: 'renseigner miaou chef de cuisine',
  creditCommitted: 'souvent',
  creditsAvailable: 'afin que au défaut de',
  headDaf: 'secours vaste pisser',
  financialController: 'formuler récupérer',
};

export const sampleWithFullData: IEngagement = {
  id: 32527,
  engagementNumber: 'lorsque confirmer ronron',
  engagementDate: dayjs('2025-03-01T16:12'),
  objectOfExpense: 'sauf à',
  notifiedCredits: 'au-dessous de sauvage',
  creditCommitted: 'bzzz près de amorcer',
  creditsAvailable: "à l'égard de grâce à affronter",
  amountProposedCommitment: "pis à l'insu de",
  headDaf: 'de façon que aïe presque',
  financialController: 'membre à vie avant',
  generalManager: 'badaboum',
};

export const sampleWithNewData: NewEngagement = {
  engagementNumber: 'davantage par rapport à',
  engagementDate: dayjs('2025-03-02T10:10'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
