import './home.scss';

import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';

import { connect } from 'react-redux';
import { Row, Col, Alert } from 'reactstrap';
import { getCoins, getUserPortfolio, uploadCoinbaseProFile } from '../administration/user-management/user-management.reducer';
import NumberFormat from 'react-number-format';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

export interface IHomeProp extends StateProps, DispatchProps, RouteComponentProps<{ login: string }> {}

export const Home = (props: IHomeProp) => {
  let { coins, portfolio } = props;
  let fileToUpload = null;
  const { account } = props;

  const getUserCoins = () => {
    if (!coins) {
      props.getCoins();
    }
    if (!portfolio && account && account.login) {
      props.getUserPortfolio(account.login);
    }
  };

  const refresh = () => {
    portfolio = coins = null;
    getUserCoins();
  };

  useEffect(() => {
    if (account && account.login) {
      getUserCoins();
    }
  });

  const filesSelected = (e: any) => {
    fileToUpload = e.target.files[0];
  };

  const uploadSelectedFile = () => {
    props.uploadCoinbaseProFile(fileToUpload);
  };

  return (
    <div>
      {account && account.login ? (
        <div className="container">
          <div className="jumbotron">
            <h1>
              <span>{account.firstName + "'s "} Portfolio </span>
              <button className="btn btn-primary" disabled={!account || !account.login} onClick={refresh}>
                <FontAwesomeIcon icon="sync"></FontAwesomeIcon> Refresh
              </button>
            </h1>
            <hr />
            <Row>
              <Col md="6">
                <label>
                  <h3>
                    <u>Portfolio total</u>
                  </h3>
                </label>
                <br />
                {portfolio ? (
                  <span>
                    <NumberFormat
                      value={portfolio.portfolioTotal}
                      displayType={'text'}
                      thousandSeparator={true}
                      prefix={'$'}
                      decimalScale={2}
                    />{' '}
                    (
                    <NumberFormat
                      value={portfolio.portfolioTotalGainLoss}
                      displayType={'text'}
                      thousandSeparator={true}
                      prefix={(portfolio.portfolioTotalGainLoss > 0 ? '+' : '') + '$'}
                      decimalScale={2}
                      className={portfolio.portfolioTotalGainLoss > 0 ? 'text-success' : 'text-danger'}
                    />
                    )
                  </span>
                ) : (
                  <span>Failed to load portfolio</span>
                )}
              </Col>
              <Col md="6">
                <div className="file-select">
                  <h4>Upload CSV</h4>
                  <input className="file-input" type="file" onChange={filesSelected} />
                  <button className="btn btn-primary" onClick={uploadSelectedFile}>
                    <FontAwesomeIcon icon="file"></FontAwesomeIcon>Upload
                  </button>
                </div>
              </Col>
            </Row>
            <hr />
            <label>
              <h3>
                <u>Wallet</u>
              </h3>
            </label>
            <Row>
              {portfolio?.userCoins ? (
                portfolio.userCoins
                  .sort((a, b) => (a.quantity * a.currentCost < b.quantity * b.currentCost ? 1 : -1))
                  .map(userCoin => (
                    <Col md="6" key={userCoin.coinSymbol}>
                      <b>
                        {userCoin.coinSymbol} - {coins ? coins.find(a => a.symbol === userCoin.coinSymbol).name : ''} - Current Price:{' '}
                        <NumberFormat
                          value={coins ? coins.find(a => a.symbol === userCoin.coinSymbol).cost : ''}
                          displayType={'text'}
                          thousandSeparator={true}
                          prefix={'$'}
                          decimalScale={2}
                        />
                      </b>
                      <hr />
                      <ul>
                        <li>
                          <span>Total: </span>
                          <span>
                            <NumberFormat
                              value={userCoin.quantity * userCoin.currentCost}
                              displayType={'text'}
                              thousandSeparator={true}
                              prefix={'$'}
                              decimalScale={2}
                            />{' '}
                          </span>
                          (
                          <NumberFormat
                            value={userCoin.gainLoss}
                            displayType={'text'}
                            thousandSeparator={true}
                            prefix={(userCoin.gainLoss > 0 ? '+' : '') + '$'}
                            decimalScale={2}
                            className={userCoin.gainLoss > 0 ? 'text-success' : 'text-danger'}
                          />
                          )
                        </li>
                        <li>
                          <span>Quantity: </span>
                          <NumberFormat value={userCoin.quantity} displayType={'text'} thousandSeparator={true} decimalScale={5} />
                        </li>
                        <li>
                          <span>Average Cost: </span>
                          <NumberFormat
                            value={userCoin.averageCost}
                            displayType={'text'}
                            thousandSeparator={true}
                            prefix={'$'}
                            decimalScale={2}
                          />
                        </li>
                      </ul>
                      <br />
                    </Col>
                  ))
              ) : (
                <span>Failed to load your coins</span>
              )}
            </Row>
          </div>
        </div>
      ) : (
        <Row>
          <Col md="3" className="pad">
            <span className="hipster rounded" />
          </Col>
          <Col md="9">
            <h2>Welcome, Java Hipster!</h2>
            <p className="lead">This is your homepage</p>
            {account && account.login ? (
              <div>
                <Alert color="success">You are logged in as user {account.login}.</Alert>
              </div>
            ) : (
              <div>
                <Alert color="warning">
                  If you want to
                  <span>&nbsp;</span>
                  <Link to="/login" className="alert-link">
                    {' '}
                    sign in
                  </Link>
                  , you can try the default accounts:
                  <br />- Administrator (login=&quot;admin&quot; and password=&quot;admin&quot;)
                  <br />- User (login=&quot;user&quot; and password=&quot;user&quot;).
                </Alert>

                <Alert color="warning">
                  You do not have an account yet?&nbsp;
                  <Link to="/account/register" className="alert-link">
                    Register a new account
                  </Link>
                </Alert>
              </div>
            )}
            <p>If you have any question on JHipster:</p>

            <ul>
              <li>
                <a href="https://www.jhipster.tech/" target="_blank" rel="noopener noreferrer">
                  JHipster homepage
                </a>
              </li>
              <li>
                <a href="http://stackoverflow.com/tags/jhipster/info" target="_blank" rel="noopener noreferrer">
                  JHipster on Stack Overflow
                </a>
              </li>
              <li>
                <a href="https://github.com/jhipster/generator-jhipster/issues?state=open" target="_blank" rel="noopener noreferrer">
                  JHipster bug tracker
                </a>
              </li>
              <li>
                <a href="https://gitter.im/jhipster/generator-jhipster" target="_blank" rel="noopener noreferrer">
                  JHipster public chat room
                </a>
              </li>
              <li>
                <a href="https://twitter.com/jhipster" target="_blank" rel="noopener noreferrer">
                  follow @jhipster on Twitter
                </a>
              </li>
            </ul>

            <p>
              If you like JHipster, do not forget to give us a star on{' '}
              <a href="https://github.com/jhipster/generator-jhipster" target="_blank" rel="noopener noreferrer">
                GitHub
              </a>
              !
            </p>
          </Col>
        </Row>
      )}
    </div>
  );
};

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated,
  coins: storeState.userManagement.coins,
  portfolio: storeState.userManagement.userPortfolio,
  coinbaseProFileName: storeState.userManagement.coinbaseProFileName,
});

const mapDispatchToProps = { getCoins, getUserPortfolio, uploadCoinbaseProFile };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Home);
