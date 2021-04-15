export interface IUserCoin {
  id?: any;
  coinSymbol?: string;
  total?: number;
  quantity?: number;
  averageCost?: number;
  gainLoss?: number;
  username?: string;
  currentCost?: number;
}

export const defaultValue: Readonly<IUserCoin> = {
  id: '',
  coinSymbol: '',
  total: 0.0,
  quantity: 0.0,
  averageCost: 0.0,
  gainLoss: 0.0,
  username: '',
  currentCost: 0.0,
};
