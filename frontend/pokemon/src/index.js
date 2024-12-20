import React from 'react';
import ReactDOM from 'react-dom/client';
import { Helmet } from 'react-helmet';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

import favicon from './pikachu-favicon.png';

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  <React.StrictMode>
    <Helmet>
      <title>Pokemon!</title>
      <link rel="icon" type="image/x-icon" href={favicon} />
    </Helmet>
    <h1>Pokemon!</h1>
    <App />
  </React.StrictMode>
);



// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
