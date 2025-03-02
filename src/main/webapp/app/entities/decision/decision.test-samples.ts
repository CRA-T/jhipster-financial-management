import dayjs from 'dayjs/esm';

import { IDecision, NewDecision } from './decision.model';

export const sampleWithRequiredData: IDecision = {
  id: 12184,
  decisionNumber: 'cadre coupable rapide',
  decisionDate: dayjs('2025-03-01T20:28'),
};

export const sampleWithPartialData: IDecision = {
  id: 24966,
  decisionNumber: 'de manière à ce que vétuste',
  decisionDate: dayjs('2025-03-02T08:31'),
};

export const sampleWithFullData: IDecision = {
  id: 12277,
  decisionNumber: 'bzzz cocorico',
  decisionDate: dayjs('2025-03-02T06:04'),
};

export const sampleWithNewData: NewDecision = {
  decisionNumber: 'bien que admirablement à condition que',
  decisionDate: dayjs('2025-03-01T15:07'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
