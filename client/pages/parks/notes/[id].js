import fetch from 'isomorphic-unfetch';
import Alert from '../../../components/Alert'

const Note = props => (
    <div>
      <h4> Notes </h4>
      <ul>
        {props.notes ? props.notes.map(note => (
          <li key={note.nid}>
            <Link href="/notes/[id]" as={`/notes/${note.nid}`}>
              <a>{note.title}</a>
            </Link>
          </li>
        )) : 
        <Alert type="danger" text="No note for park"/>}
      </ul>
    </div>
  )

Note.getInitialProps = async function(context) {
const { id } = context.query;
const res = await fetch(`http://165.227.90.43:8080/parkpay/parks/${id}/notes`);
const notes = await res.json();

return { notes };
}

export default Note;