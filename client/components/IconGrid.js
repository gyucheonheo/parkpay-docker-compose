
export default function IconGrid(props) {
    return (
        <section className="features-icons text-center">
        <div className="container">
          <div className="row">
            <div className="col-lg-4">
              <div className="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
                <div className="features-icons-icon d-flex">
                  <i className="fas fa-plug m-auto fa-5x text-primary"></i>
                </div>
                <h3>Pluggable</h3>
                <p className="lead mb-0">The service provides data in json via REST delivery mechanism. Plug-in your favorite taste of database and front-end!</p>
              </div>
            </div>
            <div className="col-lg-4">
              <div className="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
                <div className="features-icons-icon d-flex">
                  <i className="fas fa-book m-auto fa-5x text-primary"></i>
                </div>
                <h3>SOLID principles</h3>
                <p className="lead mb-0">Fully follow SOLID principles for CLEAN CODE!</p>
              </div>
            </div>
            <div className="col-lg-4">
              <div className="features-icons-item mx-auto mb-0 mb-lg-3">
                <div className="features-icons-icon d-flex">
                  <i className="fas fa-check m-auto fa-5x text-primary"></i>
                </div>
                <h3>Easy to Use</h3>
                <p className="lead mb-0">Ready to use with your own content, or customize the source files!</p>
              </div>
            </div>
          </div>
        </div>
      </section> 
    )
  }
  