import Layout from '../../components/MyLayout';
import Sidebar from '../../components/Sidebar';
import Link from 'next/link'
import fetch from 'isomorphic-unfetch';

const Visitor = props => (
  <Layout>
    <div className="row">
    <div className="col-md-7">
      <h1>{props.visitor.visitor.name},{props.visitor.visitor.email}</h1>
      <h2> Orders</h2>
    <table className="table">
      <thead>
        <tr>
          <th scope="col">Order Id</th>
          <th scope="col">Park Id</th>
          <th scope="col">Date</th>
          <th scope="col">Actions</th>
        </tr>
      </thead>
      <tbody>
          {props.visitor.orders.map(order => (
                <tr>
                  <td>
                    <a>{order.oid}</a>
                  </td>
                  <td>
                    <a>{order.pid}</a>
                  </td>
                  <td>
                    <a>{order.date}</a>
                  </td>
                  <td>
                    <Link href="/orders/[id]" as={`/orders/${order.oid}`}>
                      <a role="button" className="btn btn-success">View</a>
                    </Link>
                    <span/>
                    <button type="button" className="btn btn-danger">Delete</button>
                  </td>
                </tr>
            ))}

      </tbody>
    </table>
    <h2> Notes</h2>
    <table className="table">
      <thead>
        <tr>
          <th scope="col">Note Id</th>
          <th scope="col">Park Id</th>
          <th scope="col">Date</th>
          <th scope="col">Title</th>
          <th scope="col">Actions</th>
        </tr>
      </thead>
      <tbody>
          {props.visitor.notes.map(note => (
                <tr>
                  <td>
                    <a>{note.nid}</a>
                  </td>
                  <td>
                    <a>{note.pid}</a>
                  </td>
                  <td>
                    <a>{note.date}</a>
                  </td>
                  <td>
                    <a>{note.title}</a>
                  </td>
                  <td>
                    <Link href="/notes/[id]" as={`/notes/${note.nid}`}>
                      <a role="button" className="btn btn-success">View</a>
                    </Link>
                    <span/>
                    <button type="button" className="btn btn-danger">Delete</button>
                  </td>
                </tr>
            ))}

      </tbody>
    </table>
    </div>
    <Sidebar />
    </div>
  </Layout>
);

Visitor.getInitialProps = async function(context) {
  const { id } = context.query;
  const res = await fetch(`http://localhost:8080/parkpay/visitors/${id}`);
  const visitor = await res.json();

  return { visitor };
};

export default Visitor;