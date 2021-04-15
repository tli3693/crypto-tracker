import { ICoin } from './coin.model';

export interface IUserPortfolio {
  id?: any;
  portfolioTotal?: number;
  portfolioTotalGainLoss?: number;
  userCoins?: ICoin[];
}

export const defaultValue: Readonly<IUserPortfolio> = {
  id: '',
  portfolioTotal: 0.0,
  portfolioTotalGainLoss: 0.0,
  userCoins: null,
};
