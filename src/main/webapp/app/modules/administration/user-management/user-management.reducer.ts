import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';
import { IUser, defaultValue } from 'app/shared/model/user.model';
import { ICoin } from 'app/shared/model/coin.model';
import { IUserPortfolio } from 'app/shared/model/user.portfolio.model';

export const ACTION_TYPES = {
  FETCH_ROLES: 'userManagement/FETCH_ROLES',
  FETCH_USERS: 'userManagement/FETCH_USERS',
  FETCH_USERS_AS_ADMIN: 'userManagement/FETCH_USERS_AS_ADMIN',
  FETCH_USER: 'userManagement/FETCH_USER',
  CREATE_USER: 'userManagement/CREATE_USER',
  UPDATE_USER: 'userManagement/UPDATE_USER',
  DELETE_USER: 'userManagement/DELETE_USER',
  RESET: 'userManagement/RESET',
  GET_COINS: 'coins/FETCH_COINS',
  GET_USER_PORTFOLIO: 'coins/FETCH_USER_PORTFOLIO',
  UPLOAD_FILE_COINBASE_PRO: 'coins/UPLOAD_FILE_COINBASE_PRO',
};

const initialState = {
  loading: false,
  errorMessage: null,
  users: [] as ReadonlyArray<IUser>,
  authorities: [] as any[],
  user: defaultValue,
  updating: false,
  updateSuccess: false,
  totalItems: 0,
  coins: null,
  userPortfolio: null,
  coinbaseProFileName: null,
};

export type UserManagementState = Readonly<typeof initialState>;

// Reducer
export default (state: UserManagementState = initialState, action): UserManagementState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ROLES):
      return {
        ...state,
      };
    case REQUEST(ACTION_TYPES.FETCH_USERS):
    case REQUEST(ACTION_TYPES.FETCH_USERS_AS_ADMIN):
    case REQUEST(ACTION_TYPES.FETCH_USER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_USER):
    case REQUEST(ACTION_TYPES.UPDATE_USER):
    case REQUEST(ACTION_TYPES.DELETE_USER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_USERS):
    case FAILURE(ACTION_TYPES.FETCH_USERS_AS_ADMIN):
    case FAILURE(ACTION_TYPES.FETCH_USER):
    case FAILURE(ACTION_TYPES.FETCH_ROLES):
    case FAILURE(ACTION_TYPES.CREATE_USER):
    case FAILURE(ACTION_TYPES.UPDATE_USER):
    case FAILURE(ACTION_TYPES.DELETE_USER):
    case FAILURE(ACTION_TYPES.GET_COINS):
    case FAILURE(ACTION_TYPES.GET_USER_PORTFOLIO):
    case FAILURE(ACTION_TYPES.UPLOAD_FILE_COINBASE_PRO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ROLES):
      return {
        ...state,
        authorities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERS):
    case SUCCESS(ACTION_TYPES.FETCH_USERS_AS_ADMIN):
      return {
        ...state,
        loading: false,
        users: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_USER):
      return {
        ...state,
        loading: false,
        user: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_USER):
    case SUCCESS(ACTION_TYPES.UPDATE_USER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        user: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_USER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        user: defaultValue,
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    case SUCCESS(ACTION_TYPES.GET_COINS):
      return {
        ...state,
        loading: false,
        coins: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.GET_USER_PORTFOLIO):
      return {
        ...state,
        loading: false,
        userPortfolio: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.UPLOAD_FILE_COINBASE_PRO):
      return {
        ...state,
        loading: false,
        coinbaseProFileName: action.payload.data,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/users';
const adminUrl = 'api/admin/users';
const baseUrl = 'api';

// Actions
export const getUsers: ICrudGetAllAction<IUser> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_USERS,
    payload: axios.get<IUser>(requestUrl),
  };
};

export const getCoins: ICrudGetAllAction<ICoin> = () => {
  const requestUrl = `${baseUrl}/coins`;
  return {
    type: ACTION_TYPES.GET_COINS,
    payload: axios.get<ICoin>(requestUrl),
  };
};

export const getUserPortfolio: ICrudGetAllAction<IUserPortfolio> = username => {
  const requestUrl = `${baseUrl}/users/${username}/portfolio`;
  return {
    type: ACTION_TYPES.GET_USER_PORTFOLIO,
    payload: axios.get<IUserPortfolio>(requestUrl),
  };
};

export const uploadCoinbaseProFile: ICrudPutAction<File> = fileToUpload => async dispatch => {
  const requestUrl = `${baseUrl}/upload/coinbase-pro`;
  const formData = new FormData();
  formData.append('name', fileToUpload.name);
  formData.append('file', fileToUpload);
  const result = await dispatch({
    type: ACTION_TYPES.UPLOAD_FILE_COINBASE_PRO,
    payload: axios.post<File>(requestUrl, formData),
  });
  return result;
};

export const getUsersAsAdmin: ICrudGetAllAction<IUser> = (page, size, sort) => {
  const requestUrl = `${adminUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_USERS_AS_ADMIN,
    payload: axios.get<IUser>(requestUrl),
  };
};

export const getRoles = () => ({
  type: ACTION_TYPES.FETCH_ROLES,
  payload: axios.get(`api/authorities`),
});

export const getUser: ICrudGetAction<IUser> = id => {
  const requestUrl = `${adminUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_USER,
    payload: axios.get<IUser>(requestUrl),
  };
};

export const createUser: ICrudPutAction<IUser> = user => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_USER,
    payload: axios.post(adminUrl, user),
  });
  dispatch(getUsersAsAdmin());
  return result;
};

export const updateUser: ICrudPutAction<IUser> = user => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_USER,
    payload: axios.put(adminUrl, user),
  });
  dispatch(getUsersAsAdmin());
  return result;
};

export const deleteUser: ICrudDeleteAction<IUser> = id => async dispatch => {
  const requestUrl = `${adminUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_USER,
    payload: axios.delete(requestUrl),
  });
  dispatch(getUsersAsAdmin());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
