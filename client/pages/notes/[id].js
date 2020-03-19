import Layout from '../../components/MyLayout';
import fetch from 'isomorphic-unfetch';

const Note = props => (
  <Layout>
    <h1>{props.note.title}</h1>
    <ul>
      <li>{props.note.date}</li>
      <li>{props.note.text}</li>     
    </ul>
  </Layout>
);

Note.getInitialProps = async function(context) {
  const { id } = context.query;
  console.log(`${id}`)
//   const res = await fetch(`http://165.227.90.43:8080/parkpay/parks/${id}`);
//   const notes = await res.json();

//   return { notes };
}

export default Note;