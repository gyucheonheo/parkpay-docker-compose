
export default function Landing(props) {
  return (
    <div className="row align-items-center my-5">
        <div className="col-lg-6">
           <img className="img-fluid rounded mb-4 mb-lg-0" src="./email.gif" alt="landing" />
        </div>
        <div className="col-lg-5">
            <h1 className="font-weight-light">Make it easier to pay for Park fees</h1>
            <p> After a pretty long drive I arrived at one of this country's great parks. Beautiful weather, great views, couldn't wait to stretch a bit and then enjoy the view. Except that the park admission fee was to be paid by cash only, honor system: you put the money in an envelope, write the license place number of your vehicle on the envelope, and then drop the envelope in a collection box.

If you don't have the cash, tough luck, the park administrators don't seem to care too much. And if you don't have the right amount, then consider yourself lucky, you just made a "donation" to the Park system. </p>
            <a className="btn btn-primary" href="#">Learn More!</a>
        </div>
      </div>

  )
}
