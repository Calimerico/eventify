
export const storeLoadingByAction = (loading, action) => {
    let calls = action.type;
    if (action.type.endsWith("SUCCESS")) {
        calls = action.type.substring(0, action.type.length - 8);
    }
    if (action.type.endsWith("FAIL")) {
        calls = action.type.substring(0, action.type.length - 5);
    }
    return {[calls]:loading};

};

export const byId = (resource,prevState) => {
    return {...prevState,[resource.id]:resource};
}

export const removeById = (id,prevState) => {
    return Object.keys(prevState)
        .filter(entityId => entityId !== id)
        .reduce((cummulative, current) => {return {...cummulative, [current]:prevState[current]}}, {});
}

export const asMap = (resources, prevState) => {
    return resources.reduce((accumulator, currentValue) => {return {...accumulator, [currentValue.id]:currentValue}}, prevState);
}