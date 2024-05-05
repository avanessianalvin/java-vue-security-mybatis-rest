import {createStore} from 'vuex';

import common from "@/store/modules/common";
import person from "@/store/modules/person";


export default createStore(
    {
        modules: {common,person}
    }
)
