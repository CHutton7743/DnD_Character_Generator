import React, {Component} from "react";

class CharacterClassCard extends Component{
        state = {
            count: 0
        }
    render() {
        return (
            <React.Fragment>
                <span>{this.formatCount()}</span>
                <button>select</button>
            </React.Fragment>
        );
    }
    formatCount() {
            return this.state.count === 0 ? 'Zero' : this.state.count
    }
}
export default CharacterClassCard