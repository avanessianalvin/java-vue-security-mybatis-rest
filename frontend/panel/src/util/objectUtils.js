function areObjectsEqual(obj1, obj2) {
    // Check if both objects are of the same type
    if (typeof obj1 !== 'object' || typeof obj2 !== 'object') {
        return false;
    }

    // Get the keys of the objects
    const keys1 = Object.keys(obj1);
    const keys2 = Object.keys(obj2);

    // Check if the number of properties is the same
    if (keys1.length !== keys2.length) {
        return false;
    }

    // Check if the values of the properties are equal
    for (let key of keys1) {
        if (obj1[key] !== obj2[key]) {
            return false;
        }
    }

    // Objects are equal
    return true;
}

export {areObjectsEqual}