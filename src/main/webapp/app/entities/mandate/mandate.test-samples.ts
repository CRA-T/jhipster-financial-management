import dayjs from 'dayjs/esm';

import { IMandate, NewMandate } from './mandate.model';

export const sampleWithRequiredData: IMandate = {
  id: 9497,
  mandateNumber: 'novice au-dessus de',
  mandateDate: dayjs('2025-03-02T01:32'),
};

export const sampleWithPartialData: IMandate = {
  id: 23845,
  mandateNumber: 'coupable',
  mandateDate: dayjs('2025-03-01T23:02'),
  issueSlipNumber: 'instituer',
};

export const sampleWithFullData: IMandate = {
  id: 11722,
  mandateNumber: 'alentour trop',
  mandateDate: dayjs('2025-03-02T09:30'),
  issueSlipNumber: 'tellement diététiste',
  monthAndYearOfIssue: 'contre',
  supportingDocuments: 'direction',
};

export const sampleWithNewData: NewMandate = {
  mandateNumber: 'très quasi immense',
  mandateDate: dayjs('2025-03-02T03:51'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
