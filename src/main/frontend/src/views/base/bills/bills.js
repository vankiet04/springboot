import React from 'react';

class Bills extends React.Component {
    render() {
        return (
            <div>
                <h1>Bills</h1>
                <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '20px' }}>
                    <input type="text" placeholder="Search..." style={{ flex: '1', marginRight: '10px' }} />
                    <button>Filter by Date</button>
                </div>
                <table border="1" style={{ width: '100%', textAlign: 'left' }}>
                    <thead>
                        <tr>
                            <th>Bill ID</th>
                            <th>Customer</th>
                            <th>Date Created</th>
                            <th>Amount</th>
                            <th>View Details</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>001</td>
                            <td>John Doe</td>
                            <td>2023-10-01</td>
                            <td>$100.00</td>
                            <td><button>View</button></td>
                        </tr>
                        <tr>
                            <td>002</td>
                            <td>Jane Smith</td>
                            <td>2023-10-02</td>
                            <td>$150.00</td>
                            <td><button>View</button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        )
    }
}

export default Bills;