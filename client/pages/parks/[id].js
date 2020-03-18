import Layout from '../../components/MyLayout';
import Sidebar from '../../components/Sidebar';
import fetch from 'isomorphic-unfetch';
import Note from './notes/[id]'

const Post = props => (
  <Layout>
    <div className="row">
      <div className="col-md-7">
    <h3>{props.park.location_info.name}</h3>
    <ul>
      <li>Region : {props.park.location_info.region}</li>
      <li>Address : {props.park.location_info.address}</li>     
      <li>Phone : {props.park.location_info.phone}</li> 
    </ul>
    <h4>Payment Information</h4>
    <table className="table">
      <thead>
        <th scope="col">Type</th>
        <th scope="col">In-state</th>
        <th scope="col">Out-state</th>
      </thead>
      <tbody>
        <tr>
          <td>Motorcycle</td>
          <td>${props.park.payment_info.motorcycle[0]}</td>
          <td>${props.park.payment_info.motorcycle[1]}</td>
        </tr>
        <tr>
          <td>Car</td>
          <td>${props.park.payment_info.car[0]}</td>
          <td>${props.park.payment_info.car[1]}</td>
        </tr>
        <tr>
          <td>Rv</td>
          <td>${props.park.payment_info.rv[0]}</td>
          <td>${props.park.payment_info.rv[1]}</td>
        </tr>
      </tbody>
    </table>
    <Note />
    </div>
    <Sidebar />
    </div>
  </Layout>
);

Post.getInitialProps = async function(context) {
  const { id } = context.query;
  const res = await fetch(`http://localhost:8080/parkpay/parks/${id}`);
  const park = await res.json();

  return { park };
};

export default Post;