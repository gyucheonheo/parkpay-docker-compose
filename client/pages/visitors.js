import Layout from '../components/MyLayout';
import Sidebar from '../components/Sidebar';
import Link from 'next/link'
import fetch from 'isomorphic-unfetch';

const Visitors = props => (
  <Layout>
    <h3>Visitors</h3>
    <div className="row">
      <div className="col-md-7">
          <table class="table">
                <thead>
                  <tr>
                    <th scope="col">Name</th>
                    <th scope="col">E-mail</th>
                    <th scope="col">Actions</th>
                  </tr>
                </thead>
                <tbody>
                    {props.visitors.map(visitor => (
                          <tr>
                            <td>
                              <a>{visitor.name}</a>
                            </td>
                            <td>
                              <a>{visitor.email}</a>
                            </td>
                            <td>
                              <Link href="/visitors/[id]" as={`/visitors/${visitor.vid}`}>
                                <a role="button" class="btn btn-success">View</a>
                              </Link>
                              <span/>
                              <button type="button" class="btn btn-danger">Delete</button>
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

Visitors.getInitialProps = async function(context) {
  const res = await fetch(`http://165.227.90.43:8080/parkpay/visitors`);
  const visitors = await res.json();

  return { visitors };
};

export default Visitors;