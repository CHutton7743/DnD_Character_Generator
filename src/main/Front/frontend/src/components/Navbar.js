import React, {useState} from "react";
import {Link} from "react-router-dom";

function Navbar() {
    return (
        <>
            <nav className="navbar">
                <div className="navbar_container">
                    <Link to="/" className ="navbar-logo">
                        DragonForge <i className="fab fa-typo3"></i>
                    </Link>
                </div>
            </nav>
        </>
    )
}

export default Navbar;