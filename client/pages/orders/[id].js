import Layout from '../../components/MyLayout';
import Sidebar from '../../components/Sidebar';
import fetch from 'isomorphic-unfetch';

const Order = props => (
  <Layout>
      <h3>Order Id : {props.order.oid}</h3>
      <div className="row">
          <div className="col-md-7">
            <h4>General Information</h4>
            <ul>
                <li>Park Id : {props.order.pid}</li>
                <li>Amount : ${props.order.amount}</li>
                <li>Date : {props.order.date}</li>
            </ul>
            <h4> Vehicle Information</h4>
                <ul>
                    <li>State : {props.order.vehicle.state}</li>
                    <li>Plate : {props.order.vehicle.plate}</li>
                    <li>Type : {props.order.vehicle.type}</li>
                </ul>
            <h4> Visitor Information</h4>
                <ul>
                    <li> Name : {props.order.visitor.name}</li>
                    <li> E-mail : {props.order.visitor.email}</li>
                </ul>
            <h4>Payment Information</h4>
                <ul>
                    <li>Card Number : {props.order.visitor.payment_info.card}</li>
                    <li>Name on Card : {props.order.visitor.payment_info.name_on_card}</li>
                    <li>Exp Date : {props.order.visitor.payment_info.expiration_date}</li>
                    <li>Zip : {props.order.visitor.payment_info.zip}</li>
                    <li>Transaction id : {props.order.payment_processing.card_transaction_id}</li>
                    <li>Date and Time : {props.order.payment_processing.date_and_time}</li>
                </ul>
          </div>
          <Sidebar />
      </div>

  </Layout>
);

Order.getInitialProps = async function(context) {
  const { id } = context.query;
  const res = await fetch(`http://165.227.90.43:8080/parkpay/orders/${id}`);
  const order = await res.json();

  return { order };
};

export default Order;