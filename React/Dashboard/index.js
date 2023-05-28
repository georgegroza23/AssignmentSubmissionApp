/* eslint-disable no-unused-vars */

import React from 'react';
import { useLocalState } from '../util/LocalStorage';


const Dashboard = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");

    return (
        <div>
            <h1>Hello Wolrd</h1>
            <div>JWT value is: <br></br> {jwt}</div>
        </div>
    );
};

export default Dashboard;