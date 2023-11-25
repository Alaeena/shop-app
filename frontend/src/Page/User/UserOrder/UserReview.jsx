import { CommentList, AddComment } from '@/components/Modal';
import { useState } from 'react';

function UserReview({ children, orderId }) {
    const [visible1, setVisible1] = useState(false);
    const [visible2, setVisible2] = useState(false);
    const [payload, setPayload] = useState({});

    const handleClick = (payload) => {
        setPayload(payload);

        setVisible1(false);
        setVisible2(true);
    };

    const onBack = () => {
        setPayload({});
        setVisible2(false);
        setVisible1(true);
    };
    return (
        <div style={{ marginRight: 8 }} onClick={() => setVisible1(true)}>
            {children}
            {visible1 && <CommentList onClick={handleClick} orderId={orderId} setVisible={setVisible1} />}
            {visible2 && <AddComment payload={payload} onBack={onBack} />}
        </div>
    );
}

export default UserReview;
