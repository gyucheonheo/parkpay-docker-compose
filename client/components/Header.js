import Link from 'next/link'
import Router from 'next/router';
import NProgress from 'nprogress';
Router.events.on('routeChangeStart', url => {
  NProgress.start()
})
Router.events.on('routeChangeComplete', () => NProgress.done())
Router.events.on('routeChangeError', () => NProgress.done())

const linkStyle = {
  marginRight: 15
}

export default function Header() {
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="container">
      <Link href="/">
        <a className="navbar-brand">Payment System</a>
      </Link>
      <button className="navbar-toggler" type="button" data-toggle="collapse"
      data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
      aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
     </button>

  <div className="collapse navbar-collapse" id="navbarSupportedContent">
    <ul className="navbar-nav ml-auto">
      {/* ml-auto set menus to the right */}
      {/* mr-auto set menus to the left */}
      <li className="nav-item active">
      <Link href="/parks">
        <a style={linkStyle}>Parks</a>
      </Link>
      </li>
      <li className="nav-item">
      <Link href="/orders">
        <a style={linkStyle}>Orders</a>
      </Link>
      </li>
      <li className="nav-item">
      <Link href="/visitors">
        <a style={linkStyle}>Visitors</a>
      </Link>
      </li>
      <li className="nav-item">
      <Link href="/reports">
        <a style={linkStyle}>Reports</a>
      </Link>
      </li>
    </ul>
    </div>
  </div>
</nav>
)
}
