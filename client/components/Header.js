import Link from 'next/link'
import Router from 'next/router';
import NProgress from 'nprogress';
import { Navbar, Nav } from 'react-bootstrap';
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
    <Navbar bg="light" expand="lg">
      <Link href="/">
        <Navbar.Brand href="#home">
            Payment System
        </Navbar.Brand>
      </Link>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="ml-auto">
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
        </Nav>
     </Navbar.Collapse>
    </Navbar>
)
}
