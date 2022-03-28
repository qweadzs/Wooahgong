import { Button, Space, Row, Col } from 'antd';
import styled from 'styled-components';

export const StyledUpdateBody = styled.div`
  /* height: 60px; */
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 1rem;
`;

export const CenterAlignedSpace = styled(Space)`
  align-items: center;
  gap: 1rem;
`;

export const UploadButton = styled(Button)`
  border-radius: 5px;
  &:hover {
    color: #9088f3;
    border-color: #9088f3;
  }
`;

export const StyledUpdateInfo = styled.div`
  margin: 2rem 2rem 0 2rem;
`;
export const StyledInfoRow = styled(Row)`
  height: 3rem;
`;

export const StyledInfoTitle = styled(Col)`
  font-weight: bold;
`;

export const UnderlinedDiv = styled.div`
  border-bottom: 1px solid #b3a1e0;
`;

export const Warning = styled.div`
  color: red;
  font-size: small;
`;

export const LeaveButton = styled.div`
  position: absolute;
  left: 2rem;
  bottom: 2rem;
  color: lightgray;
  &:hover {
    cursor: pointer;
  }
`;