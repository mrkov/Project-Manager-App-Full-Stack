import React from 'react';
import './App.css';
import Dashboard from './components/Dashboard';
import Header from './components/Layout/Header';
import 'bootstrap/dist/css/bootstrap.min.css';

import { BrowserRouter, Route } from "react-router-dom";
import AddProject from './components/Project/AddProject';
import { Provider } from 'react-redux'
import store from './store';
import UpdateProject from './components/Project/UpdateProject';

function App() {
  return (
    <Provider store={store}>
      <BrowserRouter >
        <div className="App">
          <Header />
          <Route exact path='/dashboard' component={Dashboard} />
          <Route exact path='/addProject' component={AddProject} />
          <Route exact path='/updateProject/:projectId' component={UpdateProject} />
        </div>
      </BrowserRouter>
    </Provider>
  );
}

export default App;
