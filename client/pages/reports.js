import Layout from '../components/MyLayout';
import Link from 'next/link'
import fetch from 'isomorphic-unfetch';

const Reports = props => (
  <Layout>
    <h3>Reports</h3>
    <ul>
        {props.reports ? 
            props.reports.map(report => (
                <li key={report.rid}>
                    <Link href="/reports/[id]" as={`/reports/${report.rid}`}>
                        <a>{report.name}</a>
                    </Link>
                </li>
            ))
            :
            <Alert type="danger" text="No available reports yet!"></Alert>
        }
    </ul>
  </Layout>
);

Reports.getInitialProps = async function(context) {
  const res = await fetch(`http://165.227.90.43:8080/parkpay/reports`);
  const reports = await res.json();

  return { reports };
};

export default Reports;