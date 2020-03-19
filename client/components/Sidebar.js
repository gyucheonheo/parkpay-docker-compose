import Link from 'next/link'
import SearchInput from './SearchInput';
export default function Sidebar(props) {
  return (
    <div className="col-md-4">
        <SearchInput />
    
      <div className="card my-4">
        <h5 className="card-header">Categories</h5>
        <div className="card-body">
          <div className="row">
            <div className="col-lg-6">
              <ul className="list-unstyled mb-0">
                <li>
                    <Link href="/parks/registration">
                        <a>Add Park</a>
                    </Link>
                </li>
                <li>
                  <a href="#">Add Note</a>
                </li>
                <li>
                  <Link href="/orders/create">
                    <a>Add Order</a>
                  </Link>
                </li>
              </ul>
            </div>
            <div className="col-lg-6">
              <ul className="list-unstyled mb-0">
                <li>
                  {/* <a href="#">JavaScript</a> */}
                </li>
                <li>
                  {/* <a href="#">CSS</a> */}
                </li>
                <li>
                  {/* <a href="#">Tutorials</a> */}
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      </div>
  )
}

