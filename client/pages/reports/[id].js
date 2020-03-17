import Layout from '../../components/MyLayout';
import fetch from 'isomorphic-unfetch';

const Report = props => (
  <Layout>
    <h1>{props.report.name}</h1>
    <h2>start date : {props.report.start_date}</h2>
    <h2>end date : {props.report.end_date}</h2>
    {props.report.total_admissions ? 
    <h2>Total admission : {props.report.total_admissions}</h2>
    :
    <h2> Total Orders : {props.report.total_orders}</h2>
    }
    {props.report.total_revenue ?
    <h2> Total Revenue : {props.report.total_revenue}</h2>
    :
    <h2></h2>
    }
    <ul>
        {props.report.detail_by_park ? 
            props.report.detail_by_park.map(detail => (
                <li key={detail.pid}>
                    {detail.name} , {detail.admissions} , {detail.revenue}
                </li>
            ))
            :
            "No detail by park yet"
        }
    </ul>
  </Layout>
);

Report.getInitialProps = async function(context) {
  const { id } = context.query;
  const res = await fetch(`http://localhost:8080/parkpay/reports/${id}`);
  const report = await res.json();

  return { report };
};

export default Report;