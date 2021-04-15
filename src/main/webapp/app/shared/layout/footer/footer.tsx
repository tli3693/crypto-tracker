import './footer.scss';

import React from 'react';

import { Col, Row } from 'reactstrap';

const Footer = props => (
  <div className="footer page-content">
    <Row>
      <Col md="12">
        <p className="container text-center">© 2021 Jake dumb</p>
      </Col>
    </Row>
  </div>
);

export default Footer;
