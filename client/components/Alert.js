export default function Alert(props) {
    const className=`alert alert-${props.type}`
    return(
        <div className={className} role="alert">
            {props.text}
        </div>
    )
}