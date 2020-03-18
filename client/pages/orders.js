import Layout from '../components/MyLayout';
import Sidebar from '../components/Sidebar';
import Link from 'next/link'
import fetch from 'isomorphic-unfetch';

const Orders = (props) => (
  <Layout>
    <h3>Orders</h3>
      <div className="row">
          <div className="col-md-7">
            <table className="table">
            <thead>
              <tr>
                <th scope="col">Order Id</th>
                <th scope="col">Park Id</th>
                <th scope="col">Date</th>
                <th scope="col">Type</th>
                <th scople="col">Amount</th>
                <th scope="col">Actions</th>
              </tr>
            </thead>
            <tbody>
                {props.orders.map(order => (
                      <tr>
                        <td>
                          <a>{order.oid}</a>
                        </td>
                        <td>
                          <Link href="/parks/[id]" as={`/parks/${order.pid}`}>
                            <a>{order.pid}</a>
                          </Link>
                        </td>
                        <td>
                          <a>{order.date}</a>
                        </td>
                        <td>
                          <a>{order.type}</a>
                        </td>
                        <td>
                          <a>{order.amount}</a>
                        </td>
                        <td>
                          <Link href="/orders/[id]" as={`/orders/${order.oid}`}>
                            <a role="button" className="btn btn-success">View</a>
                          </Link>
                          <span/>

                        </td>
                      </tr>
                  ))}

            </tbody>
          </table>
          </div>
        <Sidebar />
        </div>
        </Layout>
)
Orders.getInitialProps = async function(context) {
  const res = await fetch(`http://localhost:8080/parkpay/orders`);
  const orders = await res.json();

  return { orders };
};

export default Orders;
