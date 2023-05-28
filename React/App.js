/* eslint-disable no-template-curly-in-string */
import './App.css';
import { Route, Routes } from 'react-router-dom';
import Homepage from './Homepage';
import Dashboard from './Dashboard';
import Login from './Login';
import PrivateRoute from './PrivateRoute';



function App() {
  return (
    <Routes>
      <Route path = "/dashboard" element = {
        <PrivateRoute>
          <Dashboard/>
        </PrivateRoute>
      } 
      />
      <Route path = '/login' element = {<Login/>}/>
      <Route path = "/" element = {<Homepage/>}/>
    </Routes>
  );
}

export default App;
