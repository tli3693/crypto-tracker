export interface ICoin {
  id?: any;
  name?: string;
  symbol?: string;
  cost?: string;
}

export const defaultValue: Readonly<ICoin> = {
  id: '',
  name: '',
  symbol: '',
  cost: '',
};
