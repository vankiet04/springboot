import React from 'react'
import BillService from './BillService' // Giả sử bạn có một service để lấy dữ liệu hóa đơn

class BillsTable extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      bills: [],
    }
  }

  componentDidMount() {
    // GET ALL BILLS
    BillService.getBills()
      .then((response) => {
        const { data } = response
        this.setState({ bills: data })
      })
      .catch((error) => {
        console.error('Error fetching bills:', error)
      })
  }

  render() {
    return (
      <div>
        <h1>Danh sách hóa đơn</h1>
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Thời gian đặt hàng</th>
              <th>Tổng giá</th>
              <th>Tên khách hàng</th>
              <th>Hành động</th>
            </tr>
          </thead>
          <tbody>
            {this.state.bills.map((bill) => (
              <tr key={bill.id}>
                <td>{bill.id}</td>
                <td>{bill.order_time}</td>
                <td>{bill.total_price}</td>
                <td>{bill.customer_name}</td>
                <td>
                  <button onClick={() => this.viewDetails(bill.id)}>Xem chi tiết</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    )
  }

  viewDetails(id) {
    // Xử lý logic xem chi tiết hóa đơn
    console.log('Xem chi tiết hóa đơn:', id)
  }
}

export default BillsTable