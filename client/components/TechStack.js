
export default function TechStack(){
    return(
        <section className="testimonials text-center">
        <div className="container">
            <h2 className="mb-5">What are used?</h2>
          <div className="row">
            <div className="col-lg-4">
              <div className="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
                <div className="features-icons-icon d-flex">
                  <i className="fab fa-react m-auto fa-5x text-primary"></i>
                </div>
                <h3>NextJS and React</h3>
                <p className="lead mb-0">NextJS(SSR) servers a fully rendered page to the client. With Next.js, server rendering React applications has never been easier, no matter where your data is coming from.</p>
              </div>
            </div>
            <div className="col-lg-4">
              <div className="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
                <div className="features-icons-icon d-flex">
                  <i className="fab fa-java m-auto fa-5x text-primary"></i>
                </div>
                <h3>Jersey</h3>
                <p className="lead mb-0">Jersey RESTful Web Services framework is open source, production quality, framework for developing RESTful Web Services in Java that provides support for JAX-RS APIs and serves as a JAX-RS (JSR 311 and JSR 339) Reference Implementation.</p>
              </div>
            </div>
            <div className="col-lg-4">
              <div className="features-icons-item mx-auto mb-0 mb-lg-3">
                <div className="features-icons-icon d-flex">
                  <i className="fab fa-docker m-auto fa-5x text-primary"></i>
                </div>
                <h3>Docker</h3>
                <p className="lead mb-0">Docker simplifies and accelerates your workflow, while giving developers the freedom to innovate with their choice of tools, application stacks, and deployment environments for each project.</p>
              </div>
            </div>
          </div>
          <div className="row">
            <div className="col-lg-4">
              <div className="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
                <div className="features-icons-icon d-flex">
                  <i className="fab fa-jenkins m-auto fa-5x text-primary"></i>
                </div>
                <h3>Jenkins</h3>
                <p className="lead mb-0">Jenkins is an open source automation server which enables developers around the world to reliably build, test, and deploy their software</p>
              </div>
            </div>
            <div className="col-lg-4">
              <div className="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
                <div className="features-icons-icon d-flex">
                  <i className="fas fa-server m-auto fa-5x text-primary"></i>
                </div>
                <h3>Grizzly</h3>
                <p className="lead mb-0">Grizzly’s goal is to help developers to build scalable and robust servers using NIO as well as offering extended framework components: Web Framework (HTTP/S), WebSocket, Comet, and more!</p>
              </div>
            </div>
            <div className="col-lg-4">
              <div className="features-icons-item mx-auto mb-0 mb-lg-3">
                <div className="features-icons-icon d-flex">
                  <i className="fab fa-bootstrap m-auto fa-5x text-primary"></i>
                </div>
                <h3>Bootstrap</h3>
                <p className="lead mb-0">Bootstrap is an open source toolkit for developing with HTML, CSS, and JS. Quickly prototype your ideas or build your entire app with our Sass variables and mixins, responsive grid system, extensive prebuilt components, and powerful plugins built on jQuery. </p>
              </div>
            </div>
          </div>
        </div>
      </section> 
    )
}