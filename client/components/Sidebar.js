import Link from 'next/link'
import SearchInput from './SearchInput';
export default function Sidebar(props) {
  return (
    <div class="col-md-4">
        <SearchInput />
    
      <div class="card my-4">
        <h5 class="card-header">Categories</h5>
        <div class="card-body">
          <div class="row">
            <div class="col-lg-6">
              <ul class="list-unstyled mb-0">
                <li>
                    <Link href="/parks/registration">
                        <a>Add Park</a>
                    </Link>
                </li>
                <li>
                  <a href="#">Add Note</a>
                </li>
                <li>
                  <a href="#">Add Visitor</a>
                </li>
              </ul>
            </div>
            <div class="col-lg-6">
              <ul class="list-unstyled mb-0">
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

