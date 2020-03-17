import Sidebar from '../../components/Sidebar';
import Layout from '../../components/MyLayout';
import Link from 'next/link';
import fetch from 'isomorphic-unfetch';

const Search = props => (
  <Layout>
    <h3>Search : {props.keyword}</h3>
    <div class="row">
      <div class="col-md-7">
    <div class="card">
      <div class="card-header">
        Parks
      </div>
      {props.parks.map(park => (
          <div class="card-body">
            <h5 class="card-title">{park.location_info.name}</h5>
            <p class="card-text">{park.location_info.address}</p>
            <Link href="/parks/[id]" as={`/parks/${park.pid}`}>
            <a role="button" class="btn btn-primary">View Detail</a>
            </Link>
          </div>
      ))}
    </div>   

    <div class="card">
      <div class="card-header">
        Orders
      </div>
      {props.orders.map(order => (
          <div class="card-body">
            <h5 class="card-title">{order.oid}</h5>
            <p class="card-text">{order.date}</p>
            <Link href="/orders/[id]" as={`/orders/${order.oid}`}>
            <a role="button" class="btn btn-primary">View Detail</a>
            </Link>
          </div>
      ))}
    </div>  

    <div class="card">
      <div class="card-header">
        Visitors
      </div>
      {props.visitors.map(visitor => (
          <div class="card-body">
            <h5 class="card-title">{visitor.name}</h5>
            <p class="card-text">{visitor.email}</p>
            <Link href="/visitors/[id]" as={`/visitors/${visitor.vid}`}>
            <a role="button" class="btn btn-primary">View Detail</a>
            </Link>
          </div>
      ))}
    </div>  
        </div>
        <Sidebar />
        </div>
  </Layout>
);

Search.getInitialProps = async function(context) {
    const { keyword } = context.query;
    const res_park = await fetch(`http://localhost:8080/parkpay/parks?key=${keyword}`);
    const parks = await res_park.json();
    const res_note = await fetch(`http://localhost:8080/parkpay/notes?key=${keyword}`);
    const notes = await res_note.json();
    const res_order = await fetch(`http://localhost:8080/parkpay/notes?key=${keyword}`);
    const orders = await res_order.json(); 
    const res_visitor = await fetch(`http://localhost:8080/parkpay/visitors?key=${keyword}`);
    const visitors = await res_visitor.json()
    return { keyword, parks, orders, visitors, notes };
};

export default Search;