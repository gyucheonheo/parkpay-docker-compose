import Layout from '../components/MyLayout';
import Sidebar from '../components/Sidebar';
import Link from 'next/link';
import fetch from 'isomorphic-unfetch';
import axios from 'axios';
import Router from 'next/router';
const Parks = props => (
  <Layout>

    <div className="container">
    <h3>Parks</h3>
    <div className="row">
      <div className="col-md-7">
      <table className="table">
        <thead>
          <tr>
            <th scope="col">Name</th>
            <th scope="col">Region</th>
            <th scope="col">Actions</th>
          </tr>
        </thead>
        <tbody>
            {props.parks.map(park => (
                  <tr key={park.pid}>
                    <td>
                      <a>{park.location_info.name}</a>
                    </td>
                    <td>
                      <a>{park.location_info.region}</a>
                    </td>
                    <td>
                      <Link href="/parks/[id]" as={`/parks/${park.pid}`}>
                        <a role="button" className="btn btn-info">View</a>
                      </Link>
                      <span/>
                      <Link href="/parks/update/[id]" as={`/parks/update/${park.pid}`}>
                        <button type="button" className="btn btn-success">Update</button>
                      </Link>
                      <button type="button" className="btn btn-danger" onClick={
                          event => {
                          event.preventDefault();
                          axios.delete(`http://165.227.90.43:8080/parkpay/parks/${park.pid}`)
                          .then(()=>{
                            Router.push('/parks')
                          })
                          .catch(err => {
                            console.log(err);
                          })
                        }
                      }>Delete</button>
                    </td>
                  </tr>
              ))}

        </tbody>
      </table>
      </div>
      <Sidebar />
    </div>
    </div>
  </Layout>
);

Parks.getInitialProps = async function() {
  const res = await fetch('http://165.227.90.43:8080/parkpay/parks');
  const data = await res.json();

  return {
    parks: data
  };
};

export default Parks;
